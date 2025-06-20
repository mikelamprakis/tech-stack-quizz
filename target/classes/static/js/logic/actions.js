import { state } from '../core/state.js'; // Import state to manage answers and quiz data
import { elements } from '../ui/elements.js'; // Import elements to manipulate DOM
import { showStep, displayResults } from '../ui/uiFunctions.js'; // Import UI functions for navigation and results display
import { loadQuizzes } from '../api/api.js'; // Import API function to reload quizzes

export function selectAnswer(answerKey) {
    const question = state.questions[state.currentQuestionIndex];
    state.answers.set(question.id, answerKey);
    
    // Update button styles
    const buttons = elements.options.getElementsByTagName('button');
    Array.from(buttons).forEach(button => {
        button.classList.remove('selected');
        if (button.textContent.startsWith(answerKey)) {
            button.classList.add('selected');
        }
    });
}


export async function submitQuiz() {
    try {
        const submission = {
            quizId: state.selectedQuiz,
            sectionIds: state.selectedSections,
            answers: Object.fromEntries(state.answers)
        };

        const response = await fetch('/api/submit', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(submission)
        });

        const result = await response.json();
        showStep(elements.results);
        displayResults(result);
    } catch (error) {
        console.error('Error submitting quiz:', error);
    }
}

export function resetQuiz() {
    state.selectedQuiz = null;
    state.selectedSections = [];
    state.questions = [];
    state.currentQuestionIndex = 0;
    state.answers.clear();
    
    showStep(elements.quizSelection);
    loadQuizzes();
}
