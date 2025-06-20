import { loadQuizzes, loadSections } from '../api/api.js'; // Import API function to load quizzes
import { elements } from '../ui/elements.js';
import { startQuiz } from '../api/api.js';
import { state } from '../core/state.js'; 
import { showStep } from '../ui/uiFunctions.js';
import { nextQuestion, previousQuestion } from '../logic/navigation.js';
import { submitQuiz, resetQuiz} from '../logic/actions.js';

export function initializeEventListeners() {
    elements.selectQuizBtn.addEventListener('click', async () => {
        await loadSections(state.selectedQuiz);
        showStep(elements.sectionSelection);
    });
    
    elements.backToQuizBtn.addEventListener('click', () => {
        showStep(elements.quizSelection);
    });
    
    elements.startQuizBtn.addEventListener('click', startQuiz);

    // Add navigation event listeners
    document.querySelector('[data-action="previous"]').addEventListener('click', previousQuestion);
    document.querySelector('[data-action="next"]').addEventListener('click', nextQuestion);
    document.querySelector('[data-action="submit"]').addEventListener('click', submitQuiz);
    document.querySelector('[data-action="reset"]').addEventListener('click', resetQuiz);
}

// Initialize the application
export async function initialize() {
    initializeEventListeners();
    await loadQuizzes();
}
