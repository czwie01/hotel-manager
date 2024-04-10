<template>
  <div class="form">
    <div class="filters-selected">
      <div class="room-type-filters">
        <div v-for="type in filterStore.roomTypes" :key="type">
          <button @click="removeRoomType(type)">{{ type }} x</button>
        </div>
      </div>
      <div v-for="number in filterStore.roomNumbers" :key="number">
        <button @click="removeRoomNumber(number)">{{ number }} x</button>
      </div>
      <div v-if="filterStore.minibar !== null">
        <button @click="removeMinibarFilter">
          Minibar Filter set {{ filterStore.minibar }} x
        </button>
      </div>
    </div>
    <div class="filter-selection">
      <div v-if="filterStore.minibar === null" class="set-minibar-filter">
        <button @click="setMinibarTrue">choose Minibar Filter true</button>
        <button @click="setMinibarFalse">choose Minibar Filter false</button>
      </div>
      <button v-if="filterStore.minibar !== null" @click="toggleMinibar">
        toggle Minibar Filter
      </button>
      <div class="room-number-filter-select">
        <input
          v-model.number="roomNumberInput"
          type="number"
          min="1"
          placeholder="Room number"
        />
        <button @click="addRoomNumber">Add room number filter</button>
      </div>
      <div class="room-type-filter-select">
        <select v-model="roomTypeInput">
          <option disabled value="">Select a room type</option>
          <option
            v-for="type in roomTypesNotInFilter"
            :key="type"
            :value="type"
          >
            {{ type }}
          </option>
        </select>
        <button @click="addRoomType">Add room type filter</button>
      </div>
      <button type="button" @click="$emit('close-dialog')">Cancel</button>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref, computed } from "vue";
import { useFilterStore } from "@/stores/filterStore";
import { useRoomStore } from "@/stores/roomStore";
import { Room, RoomType } from "@/types";
const filterStore = useFilterStore();
const roomStore = useRoomStore();
const roomNumberInput = ref("");
const roomTypeInput = ref("");

defineEmits(["close-dialog"]);

const roomTypesNotInFilter = computed(() => {
  return [
    ...new Set(
      roomStore.rooms
        .filter((room: Room) => !filterStore.roomTypes.includes(room.roomType))
        .map((room: Room) => room.roomType),
    ),
  ];
});

const addRoomNumber = () => {
  if (Number(roomNumberInput.value) > 0) {
    filterStore.addRoomNumber(Number(roomNumberInput.value));
    roomNumberInput.value = "";
  }
};

const removeRoomNumber = (roomNumber: number) => {
  filterStore.removeRoomNumber(roomNumber);
};

const addRoomType = () => {
  if (roomTypeInput.value) {
    filterStore.addRoomType(roomTypeInput.value as RoomType);
    roomTypeInput.value = "";
  }
};

const removeRoomType = (roomType: string) => {
  filterStore.removeRoomType(roomType as RoomType);
};

const toggleMinibar = () => {
  filterStore.setMinibar(!filterStore.minibar);
};

const setMinibarTrue = () => {
  filterStore.setMinibar(true);
};
const setMinibarFalse = () => {
  filterStore.setMinibar(false);
};
const removeMinibarFilter = () => {
  filterStore.removeMiniBarFilter();
};
</script>
<style scoped>
.form {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: 1rem;
  padding: 1rem;
  height: 100%;
}
button:hover {
  transition: 0.2s ease;
  transform: scale(1.1);
}
.room-type-filters {
  display: flex;
  flex-direction: row;
  gap: 1rem;
}
.set-minibar-filter {
  display: flex;
  flex-direction: row;
  gap: 1rem;
}
.room-type-filter-select {
  display: flex;
  flex-flow: row-reverse;
  justify-content: space-between;
  gap: 1rem;
}
.room-number-filter-select {
  display: flex;
  flex-flow: row-reverse;
  justify-content: space-between;
  gap: 1rem;
}
.filter-selection {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.filters-selected {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

input {
  padding: 0.5rem;
  border-radius: 15px;
  border: 1px solid #ccc;
}
</style>
