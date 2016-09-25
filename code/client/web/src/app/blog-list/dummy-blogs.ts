import { Blog } from '../shared'

export const BLOGS: Blog[] = [];

for (let i = 0; i < 20; i++) {
  BLOGS.push({title: `blog${i}`});
}
