import { FilterState, RoomType } from "@/types";
import { defineStore } from "pinia";

export const useFilterStore = defineStore({
  id: "filterStore",
  state: (): FilterState => ({
    minibar: null as boolean | null,
    roomNumbers: [] as number[],
    roomTypes: [] as RoomType[],
  }),
  actions: {
    setMinibar(value: boolean) {
      this.minibar = value;
    },
    removeMiniBarFilter() {
      this.minibar = null;
    },
    addRoomNumber(roomNumber: number) {
      this.roomNumbers.push(roomNumber);
    },
    removeRoomNumber(roomNumber: number) {
      this.roomNumbers = this.roomNumbers.filter((num) => num !== roomNumber);
    },
    addRoomType(roomType: RoomType) {
      this.roomTypes.push(roomType);
    },
    removeRoomType(roomType: RoomType) {
      this.roomTypes = this.roomTypes.filter((type) => type !== roomType);
    },
  },
});
