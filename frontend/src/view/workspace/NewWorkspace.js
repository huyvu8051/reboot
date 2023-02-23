import IconButton from '@mui/material/IconButton'
import AddIcon from '@mui/icons-material/Add'
import {TextField} from '@mui/material'
import * as React from 'react'
import {useRef, useState} from 'react'
import api from '../../service/api'
import {$success} from '../../util/snackbar-utils'
import {useNavigate} from 'react-router-dom'
import {$dispatch} from "../../util/eventbus-utils";

const NewWorkspace = () => {
    const wpNmTxtField = useRef()
    const navigate = useNavigate()
    const handleCreateNewWp = () => {
        api.put('/api/v1/user/workspace/', null, {
            params: {wpNm: wpNmTxtField.current.value}
        })
            .then(resp => {
                $success(`Workspace \`${resp.title}\` created!`)
                $dispatch('ChangeWpBtn.lswp.refresh')
                //wpNmTxtField.current.value = ''
                navigate('/w/' + resp.id)
            })
    }
    //console.log('render')
    return (
        <TextField size='small' label='New workspace' sx={{marginLeft: 2, backgroundColor: 'white'}}
                   inputRef={wpNmTxtField}
                   InputProps={{
                       endAdornment: (
                           <IconButton
                               size='small'
                               aria-label='toggle password visibility'
                               onClick={handleCreateNewWp}
                           >
                               <AddIcon fontSize='small'/>
                           </IconButton>),
                       sx: {
                           paddingRight: 0
                       }
                   }}
        />
    )
}
export default NewWorkspace