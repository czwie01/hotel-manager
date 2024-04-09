export interface RoomState {
  rooms: Room[];
}

export interface Room {
  roomNumber: number;
  type: RoomType;
  hasMiniBar: boolean;
}

export enum RoomType {
  SINGLE = "SINGLE",
  DOUBLE = "DOUBLE",
  SUITE = "SUITE",
}

export interface CreateRoomPayload {
  type: RoomType;
  hasMiniBar: boolean;
}

export interface UpdateRoomTypePayload {
  newType: RoomType;
}
