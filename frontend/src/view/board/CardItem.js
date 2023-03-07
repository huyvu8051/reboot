import {Card, CardActions, CardHeader, CardMedia, Chip} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import {$error, $success} from "../../util/snackbar-utils";
import {EditOutlined, Menu, Task} from "@mui/icons-material";

import image from '../../asset/image/paella.jpg'
import Grid from "@mui/material/Grid";

const getStyle = (prov, snap) => {

    return {
        ...prov.draggableProps.style,
        ...snap.isDragging && !snap.isDropAnimating && {
            // transform: ' rotate(5deg) ' + prov.draggableProps.style.transform
        }
    }
}

export default ({provided, snapshot, item}) => {


    return (
        <Card sx={{
            cursor: 'pointer!important;',
            position: 'relative',
            width: '100%',
            mb: 1,
            '--btn-edit-icon-color': 'rgba(255, 255, 255, 0)',
            '&:hover': {
                '--btn-edit-icon-color': 'black',
                '--btn-edit-bg-color': 'rgba(255, 255, 255, 0.2)'
            }
        }}
              ref={provided.innerRef}
              {...provided.draggableProps}
              {...provided.dragHandleProps}
              style={getStyle(
                  provided,
                  snapshot
              )}
              elevation={0}>
            <IconButton size='small' sx={{
                borderRadius: 1,
                position: 'absolute',
                top: 6,
                right: 6,
                color: 'var(--btn-edit-icon-color)',
                backgroundColor: 'var(--btn-edit-bg-color)',
                '&:hover': {
                    backgroundColor: 'rgba(255, 255, 255, 0.5)',
                },
            }}
                        onClick={() => $error('icon')}>
                <EditOutlined fontSize='small'/>
            </IconButton>
            <CardMedia
                onClick={() => $success('success')}
                sx={{height: 140}}
                image={image}
                title="green iguana"
            />
            <CardHeader
                onClick={() => $success('success')}
                titleTypographyProps={{
                    fontSize: 'small'
                }}
                sx={{p: 1}}
                title={item.title}>
            </CardHeader>
            <CardActions onClick={() => $success('success')}
                         disableSpacing>
                <Grid>
                    <Chip
                        avatar={<Menu/>}
                        size='small'
                        label={'rtest'}
                    >
                    </Chip>
                    <Chip
                        avatar={<Task/>}
                        size='small'
                        label={'rtest'}
                    >
                    </Chip>


                </Grid>
            </CardActions>
        </Card>
    )
}