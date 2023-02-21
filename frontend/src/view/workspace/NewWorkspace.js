import IconButton from '@mui/material/IconButton'
import AddIcon from '@mui/icons-material/Add'
import {TextField} from '@mui/material'
import * as React from 'react'
import {useRef} from 'react'
import api from '../../service/api'
import {$success} from '../../util/snackbar-utils'
import {useNavigate} from 'react-router-dom'

const NewWorkspace = () => {
    const wpNmTxtField = useRef()
    const navigate = useNavigate()
    const handleCreateNewWp = () => {
        api.put('/api/v1/user/workspace/', {wpNm: wpNmTxtField.current.value})
            .then(resp=>{
                $success(`Workspace \`${resp.data.title}\` created!`)
                navigate('/w/' + resp.data.id)
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