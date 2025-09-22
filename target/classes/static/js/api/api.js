import { state } from '../core/state.js'; // Import state to update quiz and section data
import { handleQuizSelection, handleCategoryToggle, handleSectionSelection } from '../events/eventHandlers.js';
import { elements } from '../ui/elements.js';
import { showStep, displayQuestion } from '../ui/uiFunctions.js';

// API Functions
export async function loadQuizzes() {
    try {
        const response = await fetch('/api/quizzes');
        const quizzes = await response.json();
        
        elements.quizList.innerHTML = '';
        quizzes.forEach(quiz => {
            const quizItem = document.createElement('div');
            quizItem.className = 'quiz-item';
            quizItem.dataset.quizId = quiz.id;
            quizItem.innerHTML = `
                <div class="quiz-info">
                    <h3>${quiz.name}</h3>
                    <p>${quiz.description}</p>
                </div>
            `;
            quizItem.onclick = () => handleQuizSelection(quiz.id, quiz.name);
            elements.quizList.appendChild(quizItem);
        });
    } catch (error) {
        console.error('Error loading quizzes:', error);
    }
}

export async function loadCategories(quizId) {
    try {
        const response = await fetch(`/api/quizzes/${quizId}/categories`);
        const categories = await response.json();
        
        elements.sectionList.innerHTML = '';
        categories.forEach(category => {
            const categoryItem = document.createElement('div');
            categoryItem.className = 'category-item';
            categoryItem.dataset.categoryId = category.id;
            
            // Create category header with toggle functionality
            const categoryHeader = document.createElement('div');
            categoryHeader.className = 'category-header';
            categoryHeader.innerHTML = `
                <div class="category-info">
                    <span class="category-toggle">▶</span>
                    <h3>${category.name}</h3>
                    <span class="section-count">(${category.sections.length} sections)</span>
                </div>
            `;
            categoryHeader.onclick = () => handleCategoryToggle(category.id);
            
            // Create sections container (initially hidden)
            const sectionsContainer = document.createElement('div');
            sectionsContainer.className = 'sections-container';
            sectionsContainer.style.display = 'none';
            
            // Add sections to the container
            category.sections.forEach(section => {
                const sectionItem = document.createElement('div');
                sectionItem.className = 'section-item';
                sectionItem.dataset.sectionId = section.id;
                sectionItem.innerHTML = `
                    <div class="section-info">
                        <h4>${section.name}</h4>
                        <p>Difficulty: ${section.difficulty}</p>
                    </div>
                `;
                sectionItem.onclick = () => handleSectionSelection(section.id);
                sectionsContainer.appendChild(sectionItem);
            });
            
            categoryItem.appendChild(categoryHeader);
            categoryItem.appendChild(sectionsContainer);
            elements.sectionList.appendChild(categoryItem);
        });
    } catch (error) {
        console.error('Error loading categories:', error);
    }
}

export async function loadSections(quizId) {
    try {
        const response = await fetch(`/api/quizzes/${quizId}/sections`);
        const sections = await response.json();
        
        elements.sectionList.innerHTML = '';
        sections.forEach(section => {
            const sectionItem = document.createElement('div');
            sectionItem.className = 'section-item';
            sectionItem.dataset.sectionId = section.id;
            sectionItem.innerHTML = `
                <div class="section-info">
                    <h3>${section.name}</h3>
                    <p>Difficulty: ${section.difficulty}</p>
                </div>
            `;
            sectionItem.onclick = () => handleSectionSelection(section.id);
            elements.sectionList.appendChild(sectionItem);
        });
    } catch (error) {
        console.error('Error loading sections:', error);
    }
}

export async function startQuiz() {
    try {
        const response = await fetch(`/api/quizzes/${state.selectedQuiz}/questions?sectionIds=${state.selectedSections.join(',')}`);
        state.questions = await response.json();
        
        if (state.questions.length === 0) {
            alert('No questions found for the selected sections');
            return;
        }
        
        state.currentQuestionIndex = 0;
        state.answers.clear();
        
        showStep(elements.quizContainer);
        displayQuestion();
    } catch (error) {
        console.error('Error starting quiz:', error);
    }
}
