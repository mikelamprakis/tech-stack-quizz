import { elements } from './elements.js'; // Import elements to manipulate DOM
import { state } from '../core/state.js'; // Import state to access quiz data
import { selectAnswer } from '../logic/actions.js'

// Markdown rendering utility
function renderMarkdown(text) {
    if (!text) return '';
    
    // Check if marked library is available
    if (!window.marked) {
        console.warn('Marked library not loaded, using fallback');
        return text;
    }
    
    try {
        console.log('Original text:', text);
        
        // Check if this is a single line with multiple list items
        if (text.includes('- ') && !text.includes('\n')) {
            console.log('Detected single-line list, converting to proper list');
            const items = text.split('- ').filter(item => item.trim().length > 0);
            const listItems = items.map(item => `<li>${item.trim()}</li>`).join('');
            const html = `<ul style="margin: 0; padding-left: 1rem;">${listItems}</ul>`;
            console.log('Single-line list HTML:', html);
            return html;
        }
        
        // Check if this looks like a multi-line list
        const lines = text.split('\n').map(line => line.trim()).filter(line => line.length > 0);
        const isList = lines.every(line => line.match(/^[-*+]\s+/));
        
        if (isList) {
            console.log('Detected as multi-line list, using manual conversion');
            const listItems = lines.map(line => {
                const content = line.replace(/^[-*+]\s+/, '');
                return `<li>${content}</li>`;
            }).join('');
            const html = `<ul style="margin: 0; padding-left: 1rem;">${listItems}</ul>`;
            console.log('Multi-line list HTML:', html);
            return html;
        }
        
        // Configure marked options for better list handling
        window.marked.setOptions({
            breaks: true, // Convert line breaks to <br>
            gfm: true,    // GitHub Flavored Markdown
            headerIds: false
        });
        
        const html = window.marked.parse(text);
        console.log('Parsed HTML:', html);
        
        // Post-process to ensure lists are properly formatted
        const processedHtml = html.replace(/<ul>/g, '<ul style="margin: 0; padding-left: 1rem;">')
                   .replace(/<ol>/g, '<ol style="margin: 0; padding-left: 1rem;">')
                   .replace(/<li>/g, '<li style="margin: 0.25rem 0;">');
        
        console.log('Processed HTML:', processedHtml);
        return processedHtml;
    } catch (error) {
        console.warn('Markdown parsing failed:', error);
        return text; // Fallback to plain text
    }
}

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
    elements.question.innerHTML = renderMarkdown(question.questionText);
    elements.options.innerHTML = '';
    
    // Check if this is a multiple choice question (has comma in correct answer)
    const isMultipleChoice = question.correctAnswer.includes(',');
    console.log('Question correctAnswer:', question.correctAnswer);
    console.log('isMultipleChoice:', isMultipleChoice);
    if (isMultipleChoice) {
        const hintDiv = document.createElement('div');
        hintDiv.className = 'multiple-choice-hint';
        hintDiv.textContent = 'This question has multiple correct answers. You can select more than one option.';
        elements.options.appendChild(hintDiv);
        console.log('Added multiple choice hint');
    }
    
    // Add selection counter for all questions
    const selectionCounter = document.createElement('div');
    selectionCounter.id = 'selection-counter';
    selectionCounter.className = 'selection-counter';
    elements.options.appendChild(selectionCounter);
    
    // Initialize selection counter
    const currentAnswer = state.answers.get(question.id);
    let selectedCount = 0;
    if (currentAnswer) {
        if (typeof currentAnswer === 'string') {
            if (currentAnswer.includes(',')) {
                selectedCount = currentAnswer.split(',').length;
            } else {
                selectedCount = 1;
            }
        } else if (Array.isArray(currentAnswer)) {
            selectedCount = currentAnswer.length;
        }
    }
    
    // Update counter with initial value
    if (selectedCount === 0) {
        selectionCounter.textContent = 'No options selected';
        selectionCounter.className = 'selection-counter none-selected';
    } else if (selectedCount === 1) {
        selectionCounter.textContent = '1 option selected';
        selectionCounter.className = 'selection-counter one-selected';
    } else {
        selectionCounter.textContent = `${selectedCount} options selected`;
        selectionCounter.className = 'selection-counter multiple-selected';
    }
    
    const optionsMap = new Map(Object.entries(question.options));

    optionsMap.forEach((value, key) => {
        const button = document.createElement('button');
        const renderedValue = renderMarkdown(value);
        console.log(`Option ${key} original:`, value);
        console.log(`Option ${key} rendered:`, renderedValue);
        button.innerHTML = `<span class="option-key">${key}:</span> <span class="option-value">${renderedValue}</span>`;
        console.log(`Creating button for option: ${key} - ${value}`);
        button.onclick = () => selectAnswer(key);
        
        // Check if this option is selected (handle both single and multiple answers)
        const currentAnswer = state.answers.get(question.id);
        console.log('Checking selection for question:', question.id, 'currentAnswer:', currentAnswer);
        if (currentAnswer) {
            let selectedAnswers = [];
            if (typeof currentAnswer === 'string') {
                // Single answer or comma-separated multiple answers
                selectedAnswers = currentAnswer.split(',').map(a => a.trim());
            } else if (Array.isArray(currentAnswer)) {
                // Array of answers
                selectedAnswers = currentAnswer;
            }
            console.log('Selected answers for this question:', selectedAnswers);
            if (selectedAnswers.includes(key)) {
                button.classList.add('selected');
                console.log('Added selected class to button:', key);
            }
        }
        
        elements.options.appendChild(button);
    });

    // Add "Show Answer" button
    const showAnswerButton = document.createElement('button');
    showAnswerButton.className = 'btn btn-info show-answer-btn';
    showAnswerButton.textContent = 'Show Answer';
    showAnswerButton.addEventListener('click', () => toggleAnswerDisplay(question));
    elements.options.appendChild(showAnswerButton);
    
    // Add answer display area (initially hidden)
    const answerDisplay = document.createElement('div');
    answerDisplay.id = 'answer-display';
    answerDisplay.className = 'answer-display hidden';
    elements.options.appendChild(answerDisplay);
}

export function toggleAnswerDisplay(question) {
    const answerDisplay = document.getElementById('answer-display');
    const showAnswerBtn = document.querySelector('.show-answer-btn');
    
    if (answerDisplay.classList.contains('hidden')) {
        // Show the answer
        answerDisplay.innerHTML = `
            <div class="answer-reveal">
                <h4>Correct Answer:</h4>
                <p class="correct-answer-text">${question.correctAnswer}</p>
                ${question.explanation ? `
                    <h4>Explanation:</h4>
                    <div class="explanation-text">${renderMarkdown(question.explanation)}</div>
                ` : ''}
            </div>
        `;
        answerDisplay.classList.remove('hidden');
        showAnswerBtn.textContent = 'Hide Answer';
        showAnswerBtn.classList.add('btn-secondary');
        showAnswerBtn.classList.remove('btn-info');
    } else {
        // Hide the answer
        answerDisplay.innerHTML = '';
        answerDisplay.classList.add('hidden');
        showAnswerBtn.textContent = 'Show Answer';
        showAnswerBtn.classList.remove('btn-secondary');
        showAnswerBtn.classList.add('btn-info');
    }
}

export function hideAnswerDisplay() {
    const answerDisplay = document.getElementById('answer-display');
    const showAnswerBtn = document.querySelector('.show-answer-btn');
    
    if (answerDisplay && !answerDisplay.classList.contains('hidden')) {
        answerDisplay.innerHTML = '';
        answerDisplay.classList.add('hidden');
        if (showAnswerBtn) {
            showAnswerBtn.textContent = 'Show Answer';
            showAnswerBtn.classList.remove('btn-secondary');
            showAnswerBtn.classList.add('btn-info');
        }
    }
}

export function displayResults(result) {
    const totalQuestions = state.questions.length;
    const correctAnswers = totalQuestions - state.incorrectAnswers.length;
    const score = Math.round((correctAnswers / totalQuestions) * 100);
    
    // Display score
    elements.scoreDisplay.innerHTML = `
        <h3>Quiz Complete!</h3>
        <p>Your Score:</p>
        <div class="score">${correctAnswers} / ${totalQuestions} questions correct</div>
        <p>Points: ${result.score} / ${result.totalPossibleScore}</p>
        <p>Percentage: ${score}%</p>
    `;
    
    // Display incorrect answers if any
    if (state.incorrectAnswers.length > 0) {
        const incorrectAnswersSection = document.createElement('div');
        incorrectAnswersSection.className = 'incorrect-answers';
        incorrectAnswersSection.innerHTML = `
            <h4>Incorrect Answers (${state.incorrectAnswers.length}):</h4>
            <div class="download-section">
                <button id="download-incorrect-btn" class="btn btn-secondary">Download Incorrect Answers</button>
            </div>
            <div class="incorrect-answers-list">
                ${state.incorrectAnswers.map((answer, index) => `
                    <div class="incorrect-answer-item" data-incorrect-index="${index}" style="cursor: pointer;">
                        <h5>Question ${answer.questionNumber}:</h5>
                        <div class="question-text">${renderMarkdown(answer.question)}</div>
                        <div class="answer-comparison">
                            <p class="user-answer">Your answer: <span class="wrong">${answer.userAnswer}</span></p>
                            <p class="correct-answer">Correct answer: <span class="correct">${answer.correctAnswer}</span></p>
                        </div>
                        <p class="click-hint">Click to see full question details</p>
                    </div>
                `).join('')}
            </div>
        `;
        elements.scoreDisplay.appendChild(incorrectAnswersSection);
        
        // Add click event listeners to incorrect answer items
        const incorrectItems = document.querySelectorAll('.incorrect-answer-item');
        incorrectItems.forEach(item => {
            item.addEventListener('click', () => {
                const index = parseInt(item.getAttribute('data-incorrect-index'));
                showIncorrectAnswerModal(index);
            });
        });
        
        // Add download button event listener
        document.getElementById('download-incorrect-btn').addEventListener('click', downloadIncorrectAnswers);
    }
}

// Modal functions
export function showIncorrectAnswerModal(incorrectIndex) {
    const incorrectAnswer = state.incorrectAnswers[incorrectIndex];
    const questionIndex = incorrectAnswer.questionNumber - 1;
    const originalQuestion = state.questions[questionIndex];
    
    // Set modal title
    elements.modalQuestionTitle.textContent = `Question ${incorrectAnswer.questionNumber}`;
    
    // Set question text
    elements.modalQuestionText.innerHTML = `<h4>Question:</h4><div>${renderMarkdown(incorrectAnswer.question)}</div>`;
    
    // Display all options with highlighting
    const optionsMap = new Map(Object.entries(originalQuestion.options));
    elements.modalOptions.innerHTML = '<h4>All Options:</h4>';
    
    // Parse user and correct answers for multiple choice
    const userAnswers = incorrectAnswer.userAnswer.split(',').map(a => a.trim());
    const correctAnswers = incorrectAnswer.correctAnswer.split(',').map(a => a.trim());
    
    optionsMap.forEach((value, key) => {
        const optionDiv = document.createElement('div');
        optionDiv.className = 'modal-option';
        
        const isUserSelected = userAnswers.includes(key);
        const isCorrect = correctAnswers.includes(key);
        
        if (isUserSelected && isCorrect) {
            optionDiv.classList.add('user-selected', 'correct');
        } else if (isUserSelected && !isCorrect) {
            optionDiv.classList.add('user-selected', 'wrong');
        } else if (!isUserSelected && isCorrect) {
            optionDiv.classList.add('correct-answer');
        }
        
        optionDiv.innerHTML = `
            <span class="option-key">${key}:</span>
            <span class="option-value">${renderMarkdown(value)}</span>
            ${isUserSelected ? '<span class="badge user-badge">Your Answer</span>' : ''}
            ${isCorrect ? '<span class="badge correct-badge">Correct</span>' : ''}
        `;
        
        elements.modalOptions.appendChild(optionDiv);
    });
    
    // Set answer comparison
    elements.modalAnswerComparison.innerHTML = `
        <h4>Answer Comparison:</h4>
        <div class="modal-comparison">
            <p class="user-answer-modal">Your answer: <span class="wrong">${incorrectAnswer.userAnswer}</span></p>
            <p class="correct-answer-modal">Correct answer: <span class="correct">${incorrectAnswer.correctAnswer}</span></p>
        </div>
    `;
    
    // Set explanation if available
    if (incorrectAnswer.explanation) {
        elements.modalExplanation.innerHTML = `
            <h4>Explanation:</h4>
            <div class="explanation-text">${renderMarkdown(incorrectAnswer.explanation)}</div>
        `;
    } else {
        elements.modalExplanation.innerHTML = '';
    }
    
    // Show modal
    elements.modal.style.display = 'block';
}

export function closeModal() {
    elements.modal.style.display = 'none';
}

export function downloadIncorrectAnswers() {
    let content = '';
    
    state.incorrectAnswers.forEach((answer, index) => {
        const questionIndex = answer.questionNumber - 1;
        const originalQuestion = state.questions[questionIndex];
        
        // Add question (strip markdown for text file)
        content += `Question ${answer.questionNumber}\n`;
        content += `${stripMarkdown(answer.question)}\n\n`;
        
        // Add options
        content += `Options:\n`;
        const optionsMap = new Map(Object.entries(originalQuestion.options));
        optionsMap.forEach((value, key) => {
            content += `${key}: ${stripMarkdown(value)}\n`;
        });
        content += '\n';
        
        // Add user answer
        content += `My Answer: ${answer.userAnswer}\n`;
        
        // Add correct answer
        content += `Correct Answer: ${answer.correctAnswer}\n`;
        
        // Add explanation if available (strip markdown for text file)
        if (answer.explanation) {
            content += `Explanation: ${stripMarkdown(answer.explanation)}\n`;
        } else {
            content += `Explanation: No explanation available\n`;
        }
        
        // Add separator (except for the last question)
        if (index < state.incorrectAnswers.length - 1) {
            content += '\n--------\n\n';
        }
    });
    
    // Create and download the file
    const blob = new Blob([content], { type: 'text/plain' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `incorrect_answers_${new Date().toISOString().split('T')[0]}.txt`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    window.URL.revokeObjectURL(url);
}

// Utility function to strip markdown formatting for text files
function stripMarkdown(text) {
    if (!text) return '';
    try {
        // Create a temporary div to parse HTML and extract text
        const tempDiv = document.createElement('div');
        tempDiv.innerHTML = renderMarkdown(text);
        return tempDiv.textContent || tempDiv.innerText || '';
    } catch (error) {
        console.warn('Markdown stripping failed:', error);
        return text; // Fallback to original text
    }
}