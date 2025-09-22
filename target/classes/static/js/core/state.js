// State management
export const state = {
    questions: [],
    currentQuestionIndex: 0,
    answers: new Map(),
    selectedQuiz: null,
    selectedSections: [],
    incorrectAnswers: [], // Array to store incorrect answers
    userAnswers: {}, // Object to store user's answers for each question
    revealedAnswers: new Set(), // Track which questions have had answers revealed
    showAnswerMode: false // Track if we're currently showing an answer
};