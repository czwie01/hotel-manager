<template>
  <div class="home">
    <div class="home__content">
      <h1>Welcome to eXXellent Nights!</h1>
      <p>Explore, experience, and enjoy eXXellent nights.</p>
      <div class="navigation-links">
        <router-link to="/main" class="nav-link">View Main</router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
export default {
  name: "HomeView",
};
</script>

<style lang="scss" scoped>
.home {
  text-align: center;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  padding-top: 20vh;
  &__content {
    h1 {
      font-size: 2.5rem;
      margin-bottom: 10px;
    }
    p {
      font-size: 1.5rem;
      margin-bottom: 20px;
    }
  }
}
.home::after {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background-image: url("@/assets/pictures/homeview-background.jpg");
  background-size: cover;
  background-position: center;
  background-attachment: fixed;
  opacity: 0.4; /* Adjust this value to change the transparency */
  z-index: -1;
}

.navigation-links {
  margin-top: 30px;
}

.nav-link {
  display: inline-block;
  margin: 10px;
  padding: 10px 20px;
  color: #fff;
  background-color: var(--clr-accent);
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.2s transform 0.2s ease;
}

.nav-link:hover {
  background-color: #060b11a8;
  transform: translateY(-5px);
}
p {
  font-weight: bold;
}
</style>
