import {useSnackbar} from 'notistack'

let useSnackbarRef
export const SnackbarUtilsConfiguration = () => {
    useSnackbarRef = useSnackbar()
    return null
}
export const toast = (msg, variant = 'default') => {
    useSnackbarRef.enqueueSnackbar(msg, {variant})
}
export const success = (msg) => {
    toast(msg, 'success')
}
export const warning = (msg) => {
    toast(msg, 'warning')
}
export const info = (msg) => {
    toast(msg, 'info')
}
export const error = (msg) => {
    toast(msg, 'error')
}


