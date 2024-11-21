# 1. Base image olarak Node.js'i kullan
FROM node:18 AS build

# 2. Çalışma dizinini ayarla
WORKDIR /app

# 3. Paket dosyalarını kopyala ve bağımlılıkları yükle
COPY package*.json ./
RUN npm install

# 4. Uygulama kaynaklarını kopyala ve üretim için derle
COPY . .
RUN npm run build

# 5. Küçük ve hızlı bir web sunucusunu kullanarak build dosyalarını sun
FROM nginx:stable-alpine

# 6. Nginx yapılandırmasını kaldır ve yeni yapılandırma ekle
COPY nginx.conf /etc/nginx/nginx.conf

# 7. Derlenmiş dosyaları Nginx'e kopyala
COPY --from=build /app/dist /usr/share/nginx/html

# 8. Konteynerin Nginx ile çalışmasını sağla
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
