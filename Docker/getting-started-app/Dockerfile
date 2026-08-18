# syntax=docker/dockerfile:1

# Switching from alpine to slim (Debian-based)
FROM node:24-slim
WORKDIR /app

# Copy ONLY package files first to leverage caching
COPY package.json ./

# Install dependencies (Debian binaries usually download fine without compilers)
RUN npm install --omit=dev

# Copy the rest of your application code
COPY . .

CMD ["node", "src/index.js"]
EXPOSE 3000