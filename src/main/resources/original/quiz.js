// State management
let state = {
    questions: [],
    currentQuestionIndex: 0,
    answers: new Map(),
    selectedQuiz: null,
    selectedSections: []
};

// DOM Elements
const elements = {
    quizSelection: document.getElementById('quiz-selection'),
    sectionSelection: document.getElementById('section-selection'),
    quizContainer: document.getElementById('quiz-container'),
    results: document.getElementById('results'),
    quizList: document.querySelector('.quiz-list'),
    sectionList: document.querySelector('.section-list'),
    selectQuizBtn: document.getElementById('select-quiz-btn'),
    backToQuizBtn: document.getElementById('back-to-quiz-btn'),
    startQuizBtn: document.getElementById('start-quiz-btn'),
    questionNumber: document.getElementById('question-number'),
    question: document.getElementById('question'),
    options: document.getElementById('options'),
    scoreDisplay: document.getElementById('score-display')
};

// Event Handlers
function handleQuizSelection(quizId, quizName) {
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

function handleSectionSelection(sectionId) {
    const sectionItem = document.querySelector(`[data-section-id="${sectionId}"]`);
    sectionItem.classList.toggle('selected');
    
    if (sectionItem.classList.contains('selected')) {
        state.selectedSections.push(sectionId);
    } else {
        state.selectedSections = state.selectedSections.filter(id => id !== sectionId);
    }
    
    elements.startQuizBtn.disabled = state.selectedSections.length === 0;
}

// UI Functions
function showStep(stepElement) {
    // Hide all steps
    elements.quizSelection.style.display = 'none';
    elements.sectionSelection.style.display = 'none';
    elements.quizContainer.style.display = 'none';
    elements.results.style.display = 'none';
    
    // Show the requested step
    stepElement.style.display = 'block';
}

function displayQuestion() {
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

function displayResults(result) {
    elements.scoreDisplay.innerHTML = `
        <h3>Quiz Complete!</h3>
        <p>Your Score:</p>
        <div class="score">${result.score} / ${result.totalPossibleScore}</div>
        <p>Percentage: ${((result.score / result.totalPossibleScore) * 100).toFixed(1)}%</p>
    `;
}

// API Functions
async function loadQuizzes() {
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

async function loadSections(quizId) {
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

async function startQuiz() {
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

function selectAnswer(answerKey) {
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

function previousQuestion() {
    if (state.currentQuestionIndex > 0) {
        state.currentQuestionIndex--;
        displayQuestion();
    }
}

function nextQuestion() {
    if (state.currentQuestionIndex < state.questions.length - 1) {
        state.currentQuestionIndex++;
        displayQuestion();
    }
}

async function submitQuiz() {
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

function resetQuiz() {
    state.selectedQuiz = null;
    state.selectedSections = [];
    state.questions = [];
    state.currentQuestionIndex = 0;
    state.answers.clear();
    
    showStep(elements.quizSelection);
    loadQuizzes();
}

// Event Listeners
function initializeEventListeners() {
    elements.selectQuizBtn.addEventListener('click', async () => {
        await loadSections(state.selectedQuiz);
        showStep(elements.sectionSelection);
    });
    
    elements.backToQuizBtn.addEventListener('click', () => {
        showStep(elements.quizSelection);
    });
    
    elements.startQuizBtn.addEventListener('click', startQuiz);
}

// Initialize the application
async function initialize() {
    initializeEventListeners();
    await loadQuizzes();
}

// Start the application when the DOM is loaded
document.addEventListener('DOMContentLoaded', initialize); 