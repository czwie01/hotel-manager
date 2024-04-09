<template>
  <div class="container">
    <!-- Button to open form for adding a new room -->
    <button @click="showRoomForm = true">Add New Room</button>

    <!-- RoomForm Dialog/Modal -->
    <div v-if="showRoomForm" class="modal">
      <div class="modal-content">
        <span class="close" @click="showRoomForm = false">&times;</span>
        <room-form
          :room="currentRoom"
          @close-dialog="showRoomForm = false"
          @update-room="updateRoom"
        />
      </div>
    </div>

    <!-- RoomList Component -->
    <room-list :rooms="rooms" :room="roomToEdit" @edit-room="editRoom" />
  </div>
</template>
<script lang="ts">
import { ref, onMounted, defineComponent } from "vue";
import RoomForm from "@/components/RoomForm.vue";
import RoomList from "@/components/RoomList.vue";
import { useRoomStore } from "@/stores/roomStore";

export default defineComponent({
  components: {
    RoomForm,
    RoomList,
  },
});
const showRoomForm = ref(false);
const rooms = ref([]);
const roomToEdit = ref(null);

const roomStore = useRoomStore();

onMounted(async () => {
  await roomStore.fetchRooms();
  rooms.value = roomStore.rooms;
});

const editRoom = (room:Room) => {
  // Here, you could set the current room data to be edited and pass it as props to RoomForm
  showRoomForm.value = true;
};

function updateRoom(newRoom) {
  currentRoom.value = newRoom;
  roomStore.updateRoom(newRoom);
}
</script>
<style>
.modal {
  display: none; /* Hidden by default */
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

.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>
