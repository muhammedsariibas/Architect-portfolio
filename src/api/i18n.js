import api from './axios'

export const getAllMessages    = (locale) => api.get('/api/v1/admin/i18n/messages', { params: locale ? { locale } : {} })
export const updateMessage    = (id, data) => api.put(`/api/v1/admin/i18n/messages/${id}`, data)
export const createMessage    = (data)    => api.post('/api/v1/admin/i18n/messages', data)
