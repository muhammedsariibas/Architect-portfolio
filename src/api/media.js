import api from './axios'

export const getAllMedia    = ()         => api.get('/api/v1/media/public/all')
export const getByCategory = (category) => api.get(`/api/v1/media/public/category/${category}`)
export const uploadMedia   = (formData) => api.post('/api/v1/admin/media/upload', formData)
export const deleteMedia   = (id)       => api.delete(`/api/v1/admin/media/${id}`)
export const updateLogo    = (formData) => api.put('/api/v1/admin/media/logo', formData)
