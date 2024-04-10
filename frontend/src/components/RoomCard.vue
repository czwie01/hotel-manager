<template>
  <div class="room-card">
    <div class="room-card__imgbox">
      <img :src="roomImage" alt="Room" />
    </div>
    <div class="room-card__info">
      <h2 class="room-card__heading">Room No.: {{ room.roomNumber }}</h2>
      <p class="room-card__type">Room Type: {{ room.roomType }}</p>
      <p class="room-card__minibar">
        Minibar: {{ room.hasMiniBar ? "Yes" : "No" }}
      </p>
    </div>
  </div>
</template>

<script setup lang="ts">
import { computed, defineProps } from "vue";
import { Room, RoomType } from "@/types"; // Make sure to define and import this type
import einzelzimmer1 from "@/assets/pictures/Einzelzimmer-1.jpg";
import einzelzimmerMinibar1 from "@/assets/pictures/Einzelzimmer-minibar-1.jpg";
import doppelzimmer1 from "@/assets/pictures/Doppelzimmer-1.jpg";
import doppelzimmerMinibar1 from "@/assets/pictures/Doppelzimmer-minibar-1.jpg";
import suite1 from "@/assets/pictures/Suite-1.jpg";
import suiteMinibar1 from "@/assets/pictures/Suite-minibar-1.jpg";

const props = defineProps({
  room: {
    type: Object as () => Room,
    required: true,
  },
});
const roomImage = computed(() => {
  // Determine the correct image based on the room's properties
  switch (props.room.roomType) {
    case RoomType.SINGLE:
      return props.room.hasMiniBar ? einzelzimmerMinibar1 : einzelzimmer1;
    case RoomType.DOUBLE:
      return props.room.hasMiniBar ? doppelzimmerMinibar1 : doppelzimmer1;
    case RoomType.SUITE:
      return props.room.hasMiniBar ? suiteMinibar1 : suite1;
    default:
      return "default image path or import"; // Provide a default image if needed
  }
});
</script>

<style lang="scss" scoped>
.room-card {
  cursor: pointer;
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  transition: transform 0.2s ease;
  background-color: #c0bbbb;
}

.room-card:hover {
  transform: translateY(-5px);
}

.room-card__imgbox img {
  width: 100%;
  height: 200px; /* Adjust as needed */
  object-fit: cover;
}

.room-card__info {
  padding: 1rem;
}

.room-card__heading {
  margin: 0;
  font-size: 1.5rem;
}

.room-card__type,
.room-card__minibar {
  margin: 0.5rem 0;
  font-size: 1rem;
}

p {
  margin: 0;
  font-size: 1rem;
  font-weight: bold;
}
</style>
