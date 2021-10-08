# html-generator
 As student collage, during web-app course there was a lot of labs where i need to do some repeating tasks over and over.
 like opening a new file and write the typical basic html document setup, Along that I had S/W enginreeing course where i've been learning about systme design 
 and object oriented, you know just this stuff.

### So I decided to comeup with a thing can teach me both how to design a simple systme, You know objects messaging and data structuring system- Html-generator


How without writing a interpreter/html-parser, Still can come with a small/simple peice of software to allow me say like:
`
var template = HtmlTemplate.make();
template.generate();
`
This will create a file.html with basic template containing doctype and html parent tag with head and body in it

> Now it's not just like this!
You can add nested tag to body/head by simply say:
`
template.addToBody(Paragraph.make("Paragraph content"));
`

I think it's very easy not a complicated system, 
Yet kinda allowing me never go and create and start typeing almost a bunch of lines everytime i go to my class lab!
