import api from './axios'

export const getUsers = () => api.get('/api/v1/admin/users')
export const createUser = (user) => api.post('/api/v1/admin/users', user)
