FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
#RUN npm ci --only=production
COPY . .
EXPOSE 8080
#RUN npm run build
ENTRYPOINT npm run serve

# étape de production
#FROM nginx:stable-alpine as production-stage
#COPY webapp.conf /etc/nginx/conf.d/webapp.conf
#COPY --from=build-stage /app/dist /usr/share/nginx/html
#EXPOSE 90
#CMD ["nginx", "-g", "daemon off;"]

#FROM build-stage AS prod
#EXPOSE 8080
#CMD ["npm", "start"]
