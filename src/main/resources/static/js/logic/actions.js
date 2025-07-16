import { state } from '../core/state.js'; // Import state to manage answers and quiz data
import { elements } from '../ui/elements.js'; // Import elements to manipulate DOM
import { showStep, displayResults } from '../ui/uiFunctions.js'; // Import UI functions for navigation and results display
import { loadQuizzes } from '../api/api.js'; // Import API function to reload quizzes

export function selectAnswer(answerKey) {
    const question = state.questions[state.currentQuestionIndex];
    console.log('selectAnswer called with:', answerKey);
    console.log('Current question:', question);
    
    // Get current answers for this question (initialize as empty array if none)
    let currentAnswers = state.answers.get(question.id) || [];
    console.log('Initial currentAnswers:', currentAnswers);
    
    // Always convert to array for consistent handling
    if (typeof currentAnswers === 'string') {
        if (currentAnswers.includes(',')) {
            // Multiple answers stored as comma-separated string
            currentAnswers = currentAnswers.split(',').map(a => a.trim());
        } else {
            // Single answer stored as string
            currentAnswers = [currentAnswers];
        }
        console.log('Converted to array:', currentAnswers);
    }
    
    // Toggle the selected answer
    const answerIndex = currentAnswers.indexOf(answerKey);
    console.log('answerIndex:', answerIndex);
    
    if (answerIndex > -1) {
        // Remove answer if already selected
        currentAnswers.splice(answerIndex, 1);
        console.log('Removed answer, new currentAnswers:', currentAnswers);
    } else {
        // Add answer if not selected
        currentAnswers.push(answerKey);
        console.log('Added answer, new currentAnswers:', currentAnswers);
    }
    
    // Sort answers alphabetically for consistent comparison
    currentAnswers.sort();
    console.log('Sorted currentAnswers:', currentAnswers);
    
    // Store the answers (as array for multiple, as string for single)
    const answerValue = currentAnswers.length === 1 ? currentAnswers[0] : currentAnswers.join(',');
    console.log('Final answerValue:', answerValue);
    state.answers.set(question.id, answerValue);
    
    // Track user answer for incorrect answers feature
    state.userAnswers[state.currentQuestionIndex] = answerValue;
    
    // Update button styles
    const buttons = elements.options.getElementsByTagName('button');
    Array.from(buttons).forEach(button => {
        button.classList.remove('selected');
        const optionKeySpan = button.querySelector('.option-key');
        if (optionKeySpan) {
            const buttonKey = optionKeySpan.textContent.replace(':', '');
            console.log('Checking button:', buttonKey, 'against currentAnswers:', currentAnswers);
            if (currentAnswers.includes(buttonKey)) {
                button.classList.add('selected');
                console.log('Added selected class to button:', buttonKey);
            }
        }
    });
    
    console.log('Final state.answers:', Object.fromEntries(state.answers));
    
    // Update selection counter
    updateSelectionCounter(currentAnswers.length);
}

function updateSelectionCounter(selectedCount) {
    const counter = document.getElementById('selection-counter');
    if (counter) {
        if (selectedCount === 0) {
            counter.textContent = 'No options selected';
            counter.className = 'selection-counter none-selected';
        } else if (selectedCount === 1) {
            counter.textContent = '1 option selected';
            counter.className = 'selection-counter one-selected';
        } else {
            counter.textContent = `${selectedCount} options selected`;
            counter.className = 'selection-counter multiple-selected';
        }
    }
}

export async function submitQuiz() {
    try {
        const submission = {
            quizId: state.selectedQuiz,
            sectionIds: state.selectedSections,
            answers: Object.fromEntries(state.answers)
        };

        console.log("---submission "+ submission);

        const response = await fetch('/api/submit', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(submission)
        });

        const result = await response.json();
        
        // Calculate incorrect answers based on the backend result
        state.incorrectAnswers = [];
        
        // Find which questions were incorrect by comparing with backend result
        state.questions.forEach((question, index) => {
            const userAnswer = state.userAnswers[index];
            const correctAnswer = question.correctAnswer;
            
            // Normalize answers for comparison (same logic as backend)
            const normalizedUserAnswer = userAnswer ? userAnswer.replaceAll(/\s*,\s*/g, ',') : '';
            const normalizedCorrectAnswer = correctAnswer.replaceAll(/\s*,\s*/g, ',');
            
            if (normalizedUserAnswer !== normalizedCorrectAnswer) {
                state.incorrectAnswers.push({
                    questionNumber: index + 1,
                    question: question.questionText,
                    userAnswer: userAnswer,
                    correctAnswer: correctAnswer,
                    explanation: question.explanation || null
                });
            }
        });
        
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
    state.incorrectAnswers = [];
    state.userAnswers = {};
    state.revealedAnswers.clear();
    state.showAnswerMode = false;
    
    showStep(elements.quizSelection);
    loadQuizzes();
}
