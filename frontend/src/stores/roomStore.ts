import { defineStore } from "pinia";
import {
  CreateRoomPayload,
  Room,
  RoomState,
  RoomType,
  UpdateRoomPayload,
} from "@/types";
import { useRoomService } from "@/services/roomService";

export const useRoomStore = defineStore({
  id: "roomStore",
  state: (): RoomState => ({
    rooms: [],
  }),
  getters: {
    getRoomByNumber: (state) => {
      return (roomNumber: number) =>
        state.rooms.find((room) => room.roomNumber === roomNumber);
    },
  },
  actions: {
    async fetchRooms() {
      const rooms: Room[] | null = await useRoomService().fetchRooms();
      if (rooms) {
        this.rooms = rooms;
      } else {
        // Handle the error or set rooms to an empty array
        this.rooms = [];
      }
    },
    async createRoom(payload: CreateRoomPayload) {
      const newRoom: Room | null = await useRoomService().createRoom(payload);
      if (newRoom) {
        this.rooms.push(newRoom);
      }
      // Else, handle the failure (potentially inform the user through UI)
    },
    async updateRoom(payload: UpdateRoomPayload) {
      useRoomService().updateRoom(payload);
      const room: Room | undefined = this.rooms.find(
        (room) => room.roomNumber === payload.roomNumber,
      );
      if (room) {
        room.roomType = payload.roomType;
        room.hasMiniBar = payload.hasMiniBar;
      }
    },

    async addMiniBarToRoom(roomNumber: number) {
      await useRoomService().addMiniBarToRoom(roomNumber);
      // After successful API call, update the state as necessary
      const room: Room | undefined = this.rooms.find(
        (room) => room.roomNumber === roomNumber,
      );
      if (room) room.hasMiniBar = true;
    },
    async removeMiniBarFromRoom(roomNumber: number) {
      await useRoomService().removeMiniBarFromRoom(roomNumber);
      // Update the state
      const room: Room | undefined = this.rooms.find(
        (room) => room.roomNumber === roomNumber,
      );
      if (room) room.hasMiniBar = false;
    },
    async removeRoom(roomNumber: number) {
      await useRoomService().removeRoom(roomNumber);
      // Update the state to reflect the room removal
      this.rooms = this.rooms.filter((room) => room.roomNumber !== roomNumber);
    },
    async updateRoomType(roomNumber: number, newType: RoomType) {
      await useRoomService().updateRoomType(roomNumber, { newType });
      // Update the state
      const room: Room | undefined = this.rooms.find(
        (room) => room.roomNumber === roomNumber,
      );
      if (room) room.roomType = newType;
    },
  },
});
