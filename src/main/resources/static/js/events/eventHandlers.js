import { elements } from '../ui/elements.js'; // Import elements to attach event listeners
import {state} from '../core/state.js'

// Event Handlers
export function handleQuizSelection(quizId, quizName) {
    state.selectedQuiz = quizId;
    
    // Update UI
    document.querySelectorAll('.quiz-item').forEach(item => {
        item.classList.remove('selected');
        if (item.dataset.quizId === quizId) {
            item.classList.add('selected');
        }
    });
    
    elements.selectQuizBtn.disabled = false;
}

export function handleSectionSelection(sectionId) {
    const sectionItem = document.querySelector(`[data-section-id="${sectionId}"]`);
    sectionItem.classList.toggle('selected');
    
    if (sectionItem.classList.contains('selected')) {
        state.selectedSections.push(sectionId);
    } else {
        state.selectedSections = state.selectedSections.filter(id => id !== sectionId);
    }
    
    elements.startQuizBtn.disabled = state.selectedSections.length === 0;
}
