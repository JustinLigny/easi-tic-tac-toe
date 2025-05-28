# Stage 1: Build Angular app
FROM node:20-alpine as build
WORKDIR /app
COPY /*.json ./
COPY /*.js ./
RUN npm install
COPY /src/ ./src/
RUN npm run build --prod 

# Stage 2: Serve with Nginx
FROM nginx:alpine
RUN rm /etc/nginx/conf.d/default.conf
COPY ./nginx.conf /etc/nginx/nginx.conf 
COPY --from=build /app/dist/tic-tac-toe /usr/share/nginx/html/
EXPOSE 80