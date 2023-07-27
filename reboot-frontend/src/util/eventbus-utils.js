/**
 *
 * @param eventType
 * @param callback
 * @returns {function(): void}
 */
export function $on(eventType, callback) {
    document.addEventListener(eventType, callback)
    return () => $off(eventType, callback)
}

export function $dispatch(eventType, data) {
    const event = new CustomEvent(eventType, {
        detail: data
    })
    document.dispatchEvent(event)
}

export function $off(eventType, callback) {
    document.removeEventListener(eventType, callback)
}