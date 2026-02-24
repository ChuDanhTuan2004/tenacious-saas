import { configureStore } from '@reduxjs/toolkit';
import authReducer from './slices/authSlice';

export const store = configureStore({
    reducer: {
        auth: authReducer,
        // Thêm các reducer khác ở đây (e.g., employee, payroll...)
    },
});

export default store;
