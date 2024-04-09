import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import MainLayout from "../views/MainLayout.vue";
import RoomsView from "../views/RoomsView.vue";
import ImpressumView from "../views/ImpressumView.vue";
import ContactView from "../views/ContactView.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/main",
    component: MainLayout,
    children: [
      { path: "rooms", name: "Rooms", component: RoomsView },
      {
        path: "impressum",
        name: "ImpressumView",
        component: ImpressumView,
      },
      {
        path: "contact",
        name: "ContactView",
        component: ContactView,
      },
    ],
  },
  // ... other routes
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
