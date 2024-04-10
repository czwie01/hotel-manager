export interface Form {
  roomNumber: number | null;
  type: RoomType;
  hasMiniBar: boolean;
}

export interface FilterState {
  minibar: boolean | null;
  roomNumbers: number[];
  roomTypes: RoomType[];
}

export interface RoomState {
  rooms: Room[];
}

export interface Room {
  roomNumber: number;
  roomType: RoomType;
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

export interface UpdateRoomPayload {
  roomNumber: number;
  roomType: RoomType;
  hasMiniBar: boolean;
}

export interface UpdateRoomTypePayload {
  newType: RoomType;
}
