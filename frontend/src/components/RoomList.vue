<template>
  <div class="room-list">
    <div
      v-for="room in rooms"
      :key="room.roomNumber"
      class="room-item"
      @click="handleRoomClick(room)"
    >
      <RoomCard :room="room" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { defineProps, defineEmits } from "vue";
import RoomCard from "./RoomCard.vue";
import { Room } from "@/types";

// Props for receiving rooms array from parent
defineProps({
  rooms: {
    type: Array as () => Room[],
    required: true,
  },
});

const emit = defineEmits(["edit-room"]);

const handleRoomClick = (room: Room) => {
  console.log("Room clicked", room);
  emit("edit-room", room); // Emit an event when a room is clicked, passing the room as a payload
};
</script>

<style scoped>
.room-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 1rem;
}

.room-item {
  cursor: pointer;
  transition: transform 0.2s;
}

.room-item:hover {
  transform: scale(1.05);
}
</style>
