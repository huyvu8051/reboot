export const listSelector = sts => sts.dashboard.lizts
    .filter(e => !e.isDeleted)
    .sort((e1, e2) => e1.ordinal - e2.ordinal)


export const getMiddleVal = (v1, v2) => {
    if (v1 === v2) return v1 * 1.0 / 2
    const diff = Math.abs(v1 * 1.0 - v2)
    const pow = Math.pow(10, Math.floor(Math.log10(diff)))

    const firstDigit = diff / pow
    const rounded = Math.round(firstDigit) * pow

    if (v2 > v1) {
        return v2 - (rounded / 2)
    }

    return v1 - (rounded / 2)
}