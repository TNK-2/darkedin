export interface Tag {
  id: number;
  name: string;
  count: number;
}

export const MOCK_TAGS: Tag[] = [
  {id: 1, name: "あとで読む", count: 22},
  {id: 2, name: "技術", count: 55},
  {id: 3, name: "飯", count: 9}
]
