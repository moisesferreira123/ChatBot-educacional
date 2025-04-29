// AISuggestionExtension.js
import { Extension } from '@tiptap/core';

export default Extension.create({
  name: 'aiSuggestion',

  addOptions() {
    return {
      onTrigger: () => {},
    };
  },

  addInputRules() {
    return [
      {
        find: /@ai$/,
        handler: ({ state, range }) => {
          const pos = range.from;
          this.options.onTrigger(pos);
          
          // Replace @ai with empty space
          const tr = state.tr.deleteRange(pos, pos+2);
          return tr;
        },
      },
    ];
  },
});