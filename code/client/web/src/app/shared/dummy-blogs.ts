import { Blog } from '../shared'

export const BLOGS: Blog[] = [];

let sampleParagraph = `Angular is built by a team of engineers who share a passion for making web development feel effortless. We believe that writing beautiful apps should be joyful and fun. We're building a platform for the future.`

for (let i = 0; i <= 10; i++) {
  BLOGS.push({id: i.toString(), title: `blog${i}`, content: `sample paragraph ${i}. ${sampleParagraph}`});
}
