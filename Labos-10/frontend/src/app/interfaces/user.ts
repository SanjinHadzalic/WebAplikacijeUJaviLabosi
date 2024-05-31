import { UserRole } from "./user-roles";

export interface UserInfo {
    id: number;
    username: string;
    password: string;
    roles: UserRole[];
}
