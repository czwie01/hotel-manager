<template>
  <div class="home">
    <h1>Welcome to eXXellent Nights!</h1>
    <p>Explore, experience, and enjoy eXXellent nights.</p>
    <div class="navigation-links">
      <router-link to="/main" class="nav-link">View Main</router-link>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "HomeView",
};
</script>

<style scoped>
.home {
  text-align: center;
  margin: 20px;
  background-image: linear-gradient(to top, #757575, rgb(206, 224, 248));
}

h1 {
  margin: 20px 0;
}

.navigation-links {
  margin-top: 30px;
}

.nav-link {
  display: inline-block;
  margin: 10px;
  padding: 10px 20px;
  color: #fff;
  background-color: #007bff;
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.2s;
}

.nav-link:hover {
  background-color: #0056b3;
}
</style>
