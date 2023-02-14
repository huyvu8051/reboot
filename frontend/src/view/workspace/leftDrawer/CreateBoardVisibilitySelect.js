import {FormControl, InputLabel, MenuItem, Select} from '@mui/material'
import * as React from 'react'
import {useState} from 'react'

export default (props) => {
    const [value, setValue] = useState('')

    const handleChange = (event) => {
        setValue(event.target.value)
    }

    return (
        <>
            <FormControl fullWidth>
                <InputLabel id='demo-simple-select-label' size='small' shrink>Visibility</InputLabel>
                <Select
                    labelId='demo-simple-select-label'
                    id='demo-simple-select'
                    label='Visibility'
                    sx={{mb: 1}}
                    size='small'
                    fullWidth
                    notched
                    value={value}
                    onChange={handleChange}
                    inputRef={props.childRef}
                >

                    <MenuItem value='private'>
                        Private
                    </MenuItem>
                    <MenuItem value='workspace'>
                        Workspace
                    </MenuItem>
                    <MenuItem value='public'>
                        Public
                    </MenuItem>


                </Select>
            </FormControl>
        </>
    )
}