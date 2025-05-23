export function clickOutsideFilter( node: HTMLElement,
  callback: () => void) {
  const handleClick = (event: MouseEvent) => {
    const target = event.target as HTMLElement;
    if (node.contains(target) || target.closest('[filter-button]')) return;
    callback();
  };

  document.addEventListener('click', handleClick, true);

  return {
    destroy() {
      document.removeEventListener('click', handleClick, true);
    }
  };
}