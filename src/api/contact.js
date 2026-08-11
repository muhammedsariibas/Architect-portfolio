import api from './axios'

export const getContact    = ()     => api.get('/api/v1/contact/public')
export const updateContact = (data) => api.put('/api/v1/contact/admin', data)
