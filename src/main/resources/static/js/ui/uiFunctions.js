import { elements } from './elements.js'; // Import elements to manipulate DOM
import { state } from '../core/state.js'; // Import state to access quiz data
import { selectAnswer } from '../logic/actions.js'

// UI Functions
export function showStep(stepElement) {
    // Hide all steps
    elements.quizSelection.style.display = 'none';
    elements.sectionSelection.style.display = 'none';
    elements.quizContainer.style.display = 'none';
    elements.results.style.display = 'none';
    
    // Show the requested step
    stepElement.style.display = 'block';
}

export function displayQuestion() {
    const question = state.questions[state.currentQuestionIndex];
    elements.questionNumber.textContent = `Question ${state.currentQuestionIndex + 1} of ${state.questions.length}`;
    elements.question.textContent = question.questionText;
    elements.options.innerHTML = '';
    
    const optionsMap = new Map(Object.entries(question.options));

    optionsMap.forEach((value, key) => {
        const button = document.createElement('button');
        button.textContent = key + ': ' + value;
        console.log(`Creating button for option: ${key} - ${value}`);
        button.onclick = () => selectAnswer(key);
        if (state.answers.get(question.id) === key) {
            button.classList.add('selected');
        }
        elements.options.appendChild(button);
    });
}

export function displayResults(result) {
    elements.scoreDisplay.innerHTML = `
        <h3>Quiz Complete!</h3>
        <p>Your Score:</p>
        <div class="score">${result.score} / ${result.totalPossibleScore}</div>
        <p>Percentage: ${((result.score / result.totalPossibleScore) * 100).toFixed(1)}%</p>
    `;
}