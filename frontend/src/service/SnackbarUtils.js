import {useSnackbar} from 'notistack'
import React from 'react'

let useSnackbarRef
export const SnackbarUtilsConfiguration = () => {
    useSnackbarRef = useSnackbar()
    return null
}
const toast = (msg, variant = 'default') => {
    useSnackbarRef.enqueueSnackbar(msg, {variant})
}
const success = (msg) => {
    toast(msg, 'success')
}
const warning = (msg) => {
    toast(msg, 'warning')
}
const info = (msg) => {
    toast(msg, 'info')
}
const error = (msg) => {
    toast(msg, 'error')
}


export default {
    success,
    warning,
    info,
    error,
    toast
}