import { Tag, MOCK_TAGS } from "./tag";

export interface User {
  id: string;
  name: string;
  imageUrl: string;
  tags: Tag[];
  favoriteCount: number;
}

export const MOCK_USER: User = {
  id: "yuuu1993g",
  name: "tanuki",
  imageUrl: "https://cdn.profile-image.st-hatena.com/users/yuuu1993g/profile.png",
  tags: [MOCK_TAGS[0], MOCK_TAGS[1], MOCK_TAGS[2]],
  favoriteCount: 2
}
