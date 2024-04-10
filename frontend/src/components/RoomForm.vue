<template>
  <form class="form" @submit.prevent="handleSubmit">
    <div class="form__values-close">
      <div class="form__values-close__values">
        <div v-show="room" class="room-number">
          Room Number: {{ room?.roomNumber }}
        </div>
        <div class="room-type">
          <label for="roomType">Room Type:</label>
          <select id="roomType" v-model="form.roomType">
            <option value="SINGLE">Single</option>
            <option value="DOUBLE">Double</option>
            <option value="SUITE">Suite</option>
          </select>
        </div>
        <div class="has-minibar">
          <label for="hasMiniBar">Has Minibar:</label>
          <input id="hasMiniBar" v-model="form.hasMiniBar" type="checkbox" />
        </div>
      </div>
      <span class="close" @click="closeForm">&times;</span>
    </div>
    <div class="form__buttons">
      <button type="submit">
        {{ isEditMode ? "Update Room" : "Create Room" }}
      </button>
      <button v-if="isEditMode" @click="handleRemove">
        {{ "Remove Room" }}
      </button>
      <button type="button" @click="$emit('close-dialog')">Cancel</button>
    </div>
  </form>
</template>

<script setup lang="ts">
import { ref, watch, defineProps, defineEmits } from "vue";
import { Room, RoomType } from "@/types"; // Import your Room type definition

const props = defineProps({
  room: {
    type: Object as () => Room | null,
    default: null,
  },
});
const emit = defineEmits([
  "update-room",
  "create-room",
  "close-dialog",
  "remove-room",
]);

const form = ref<{
  roomNumber: number | null;
  roomType: RoomType;
  hasMiniBar: boolean;
}>({
  roomNumber: null,
  roomType: RoomType.SINGLE,
  hasMiniBar: false,
});

// Detect edit mode based on if a room is passed as a prop
const isEditMode = ref(false);

// Watch for changes to the room prop to set form values
watch(
  () => props.room,
  (newRoom) => {
    if (newRoom) {
      isEditMode.value = true;
      form.value = { ...newRoom, roomType: newRoom.roomType };
    } else {
      isEditMode.value = false;
      resetForm();
    }
  },
  { immediate: true },
);

function handleSubmit() {
  if (isEditMode.value) {
    emit("update-room", { ...form.value });
  } else {
    // eslint-disable-next-line @typescript-eslint/no-unused-vars
    const { roomNumber, ...payloadWithoutRoomNumber } = form.value;
    emit("create-room", payloadWithoutRoomNumber);
  }
  resetForm();
  emit("close-dialog");
}

function handleRemove() {
  if (props.room) {
    emit("remove-room", props.room.roomNumber);
    resetForm();
    emit("close-dialog");
  }
}

function resetForm() {
  form.value = {
    roomNumber: null,
    roomType: RoomType.SINGLE,
    hasMiniBar: false,
  };
}

function closeForm() {
  resetForm();
  emit("close-dialog");
}
</script>

<style lang="scss" scoped>
.form {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: 1rem;
  padding: 1rem;
  &___values-close {
    &__values {
      display: flex;
      flex-direction: column;
      gap: 1rem;
    }
  }
  &__buttons {
    font-size: 1.5em;
    display: flex;
    flex-direction: column;
    gap: 1rem;
    justify-content: flex-end;
    flex-wrap: nowrap;
    align-content: center;
    align-items: stretch;
  }
}
.form__values-close {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.form__values-close__values {
  display: flex;
  flex-direction: column;
  gap: 2rem;
  font-size: 1.25em;
  font-weight: bold;
}
.close {
  color: #aaa;
  float: right;
  font-size: 35px;
  font-weight: bold;
  align-self: flex-start;
}
.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
.room-type {
  display: flex;
  flex-direction: row;
  gap: 1rem;
  align-items: center;
}
input[type="checkbox"] {
  width: 1.25em;
  height: 1.25em;
}

select {
  font-size: 1em;
  padding: 0.5em;
  border-radius: 15px;
  border: 2px solid #ccc;
  color: #333;
  font-weight: bold;
  cursor: pointer;
}

.has-minibar {
  display: flex;
  flex-direction: row;
  gap: 1rem;
  align-items: center;
}
</style>
