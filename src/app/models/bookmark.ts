import { Tag } from './tag';

export interface Bookmark {
  id: number;
  title: string;
  url: string;
  registDate: Date;
  isFavorite: boolean;
  tags: Tag[];
  memo: string;
}
