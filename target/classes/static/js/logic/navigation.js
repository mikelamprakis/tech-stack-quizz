import { state } from '../core/state.js'; // Import state to access currentQuestionIndex and questions
import { displayQuestion, hideAnswerDisplay } from '../ui/uiFunctions.js'; // Import displayQuestion to update the UI

export function previousQuestion() {
    if (state.currentQuestionIndex > 0) {
        // Hide any currently displayed answer
        hideAnswerDisplay();
        state.currentQuestionIndex--;
        displayQuestion();
    }
}

export function nextQuestion() {
    if (state.currentQuestionIndex < state.questions.length - 1) {
        // Hide any currently displayed answer
        hideAnswerDisplay();
        state.currentQuestionIndex++;
        displayQuestion();
    }
}