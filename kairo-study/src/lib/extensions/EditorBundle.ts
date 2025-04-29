//TODO: Bundle all the editor extensions into a single file
import { Extension } from '@tiptap/core';


export const EditorBundle = Extension.create({
  name: 'editorBundle',
  addExtensions() {
    return [
      //TODO: Add editor extensions here
    ];
  },
})