import axios from "axios";
import { Room, CreateRoomPayload, UpdateRoomTypePayload } from "@/types"; // Define these types according to your needs

const baseUrl = "http://localhost:4200/rooms";

export function useRoomService() {
  const fetchRooms = async (): Promise<Room[] | null> => {
    try {
      const response = await axios.get(`${baseUrl}/list`);
      return response.data;
    } catch (error) {
      console.error("Failed to fetch rooms:", error);
      // Optionally, return null or an empty array depending on how you want to handle this in your UI
      return null;
    }
  };

  const createRoom = async (
    payload: CreateRoomPayload,
  ): Promise<Room | null> => {
    try {
      const response = await axios.post(baseUrl, payload);
      return response.data;
    } catch (error) {
      console.error("Failed to create room:", error);
      // Handle the error appropriately, perhaps by informing the user through the UI
      return null;
    }
  };

  // The rest of your service methods with similar error handling...

  const addMiniBarToRoom = async (roomNumber: number): Promise<void> => {
    try {
      await axios.put(`${baseUrl}/addMiniBar/${roomNumber}`);
    } catch (error) {
      console.error(`Failed to add minibar to room ${roomNumber}:`, error);
      // Additional error handling as needed
    }
  };

  const removeMiniBarFromRoom = async (roomNumber: number): Promise<void> => {
    try {
      await axios.put(`${baseUrl}/removeMiniBar/${roomNumber}`);
    } catch (error) {
      console.error(`Failed to remove minibar from room ${roomNumber}:`, error);
      // Additional error handling as needed
    }
  };

  const removeRoom = async (roomNumber: number): Promise<void> => {
    try {
      await axios.delete(`${baseUrl}/remove/${roomNumber}`);
    } catch (error) {
      console.error(`Failed to remove room ${roomNumber}:`, error);
      // Additional error handling as needed
    }
  };

  const updateRoomType = async (
    roomNumber: number,
    payload: UpdateRoomTypePayload,
  ): Promise<void> => {
    try {
      await axios.put(`${baseUrl}/updateType/${roomNumber}`, payload);
    } catch (error) {
      console.error(
        `Failed to update room type for room ${roomNumber}:`,
        error,
      );
      // Additional error handling as needed
    }
  };

  return {
    fetchRooms,
    createRoom,
    addMiniBarToRoom,
    removeMiniBarFromRoom,
    removeRoom,
    updateRoomType,
  };
}
