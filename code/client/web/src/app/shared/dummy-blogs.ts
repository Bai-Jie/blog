import { Blog } from '../shared'

export const BLOGS: Blog[] = [];

let sampleParagraph = `Angular is built by a team of engineers who share a passion for making web development feel effortless. We believe that writing beautiful apps should be joyful and fun. We're building a platform for the future.`

for (let i = 0; i < 20; i++) {
  BLOGS.push({title: `blog${i}`, content: `sample paragraph ${i}. ${sampleParagraph}`});
}
