export function clickOutside( node: HTMLElement,
  callback: () => void) {
  const handleClick = (event: MouseEvent) => {
    const target = event.target as HTMLElement;
    if (node.contains(target) || target.closest('[data-user-button]')) return;
    callback();
  };

  document.addEventListener('click', handleClick, true);

  return {
    destroy() {
      document.removeEventListener('click', handleClick, true);
    }
  };
}
