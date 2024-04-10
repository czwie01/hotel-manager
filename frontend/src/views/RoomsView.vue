<template>
  <div class="container">
    <div class="buttons-and-filters">
      <!-- Button to open form for adding a new room -->
      <button class="add-room-button" @click="showRoomForm = true">
        Add New Room
      </button>
      <button class="add-filter-button" @click="showFilterForm = true">
        Add/Remove Filter
      </button>
      <div></div>
    </div>
    <!-- RoomForm Dialog/Modal -->
    <div v-if="showFilterForm" class="filter-modal">
      <div class="filter-modal-content">
        <filter-form :room="roomToEdit" @close-dialog="closeFilterDialog" />
      </div>
    </div>
    <!-- RoomForm Dialog/Modal -->
    <div v-if="showRoomForm" class="room-modal">
      <div class="room-modal-content">
        <room-form
          :room="roomToEdit"
          @close-dialog="closeRoomDialog"
          @update-room="updateRoom"
          @create-room="createRoom"
          @remove-room="removeRoom"
        />
      </div>
    </div>

    <!-- RoomList Component -->
    <room-list :rooms="filteredRooms" @edit-room="editRoom" />
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted, computed } from "vue";
import FilterForm from "@/components/FilterForm.vue";
import RoomForm from "@/components/RoomForm.vue";
import RoomList from "@/components/RoomList.vue";
import { useRoomStore } from "@/stores/roomStore";
import { useFilterStore } from "@/stores/filterStore";
import { CreateRoomPayload, Room } from "@/types";
const showRoomForm = ref(false);
const showFilterForm = ref(false);
const roomToEdit = ref<Room | null>(null);
const roomStore = useRoomStore();
const filterStore = useFilterStore();

onMounted(async () => {
  await roomStore.fetchRooms();
});

const editRoom = (room: Room) => {
  roomToEdit.value = room;
  showRoomForm.value = true;
  console.log("Editing room", showRoomForm.value);
};

function updateRoom(roomToUpdate: Room) {
  console.log("Updating room", roomToUpdate);
  roomStore.updateRoom({ ...roomToUpdate });
  roomToEdit.value = null;
}

function createRoom(roomToCreate: CreateRoomPayload) {
  console.log("Creating room", roomToCreate);
  roomStore.createRoom(roomToCreate);
}

function removeRoom(roomNumber: number) {
  console.log("Deleting room", roomNumber);
  roomStore.removeRoom(roomNumber);
}

function closeRoomDialog() {
  showRoomForm.value = false;
  roomToEdit.value = null;
}

function closeFilterDialog() {
  showFilterForm.value = false;
}

const filteredRooms = computed(() => {
  return roomStore.rooms.filter((room) => {
    // If minibar filter is set and doesn't match the room's minibar status, exclude the room
    if (
      filterStore.minibar !== room.hasMiniBar &&
      filterStore.minibar !== null
    ) {
      return false;
    }
    // If room numbers are selected and the room's number is not in the selection, exclude the room
    if (
      filterStore.roomNumbers.length > 0 &&
      !filterStore.roomNumbers.includes(room.roomNumber)
    ) {
      return false;
    }
    // If room types are selected and the room's type is not in the selection, exclude the room
    if (
      filterStore.roomTypes.length > 0 &&
      !filterStore.roomTypes.includes(room.roomType)
    ) {
      return false;
    }
    // If none of the above conditions are met, include the room
    return true;
  });
});
</script>
<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  gap: 1rem;
}
.filter-modal {
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}
.filter-modal-content {
  border-radius: 25px;
  border-style: solid 1px;
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 450px; /* Could be more or less, depending on screen size */
  height: 450px;
  box-shadow: 20px 46px 93px 20px rgba(43, 43, 43, 0.45);
}

.room-modal {
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

.room-modal-content {
  border-radius: 25px;
  border-style: solid 1px;
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 450px; /* Could be more or less, depending on screen size */
  height: 500px;
  box-shadow: 20px 46px 93px 20px rgba(43, 43, 43, 0.45);
}
.close {
  color: #aaa;
  float: right;
  font-size: 35px;
  font-weight: bold;
}
.buttons-and-filters {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  gap: 1rem;
}
button {
  width: 200px;
}
.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
@media (max-width: 768px) {
  .room-modal-content {
    width: 300px;
    height: 400px;
  }
  .filter-modal-content {
    width: 300px;
    height: 600px;
  }
  button {
    width: 150px;
  }
}
</style>
