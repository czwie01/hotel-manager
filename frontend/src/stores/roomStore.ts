import { defineStore } from "pinia";
import { CreateRoomPayload, Room, RoomState, RoomType } from "@/types";
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
      const roomService = useRoomService();
      const rooms: Room[] | null = await roomService.fetchRooms();
      if (rooms) {
        this.rooms = rooms;
      } else {
        // Handle the error or set rooms to an empty array
        this.rooms = [];
      }
    },
    async createRoom(payload: CreateRoomPayload) {
      const roomService = useRoomService();
      const newRoom: Room | null = await roomService.createRoom(payload);
      if (newRoom) {
        this.rooms.push(newRoom);
      }
      // Else, handle the failure (potentially inform the user through UI)
    },
    async addMiniBarToRoom(roomNumber: number) {
      const roomService = useRoomService();
      await roomService.addMiniBarToRoom(roomNumber);
      // After successful API call, update the state as necessary
      const room: Room | undefined = this.rooms.find(
        (room) => room.roomNumber === roomNumber,
      );
      if (room) room.hasMiniBar = true;
    },
    async removeMiniBarFromRoom(roomNumber: number) {
      const roomService = useRoomService();
      await roomService.removeMiniBarFromRoom(roomNumber);
      // Update the state
      const room: Room | undefined = this.rooms.find(
        (room) => room.roomNumber === roomNumber,
      );
      if (room) room.hasMiniBar = false;
    },
    async removeRoom(roomNumber: number) {
      const roomService = useRoomService();
      await roomService.removeRoom(roomNumber);
      // Update the state to reflect the room removal
      this.rooms = this.rooms.filter((room) => room.roomNumber !== roomNumber);
    },
    async updateRoomType(roomNumber: number, newType: RoomType) {
      const roomService = useRoomService();
      await roomService.updateRoomType(roomNumber, { newType });
      // Update the state
      const room: Room | undefined = this.rooms.find(
        (room) => room.roomNumber === roomNumber,
      );
      if (room) room.type = newType;
    },
  },
});
