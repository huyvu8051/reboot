import {Card, CardActions, CardHeader, CardMedia} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import {$error, $success} from "../../util/snackbar-utils";
import {EditOutlined} from "@mui/icons-material";

const getItemStyle = (isDragging, draggableStyle) => ({
    // some basic styles to make the items look a bit nicer
    //userSelect: "none",
    // change background colour if dragging
    transform: 'rotate(20deg);',
    ...draggableStyle,

    // styles we need to apply on draggables
});

export default ({provided, snapshot, item}) => {


    return (
        <Card sx={{
            width: '100%',
            mb:1,
            '--btn-edit-icon-color': 'rgba(255, 255, 255, 0)',
            '&:hover': {
                '--btn-edit-icon-color': 'black',
                '--btn-edit-bg-color': 'rgba(255, 255, 255, 0.2)'
            }
        }}
              ref={provided.innerRef}
              {...provided.draggableProps}
              {...provided.dragHandleProps}
              style={getItemStyle(
                  snapshot.isDragging,
                  provided.draggableProps.style
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
                image="https://picsum.photos/200/300"
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
                {/*<Grid>*/}
                {/*    <Chip*/}
                {/*        avatar={<Menu/>}*/}
                {/*        size='small'*/}
                {/*        label={'rtest'}*/}
                {/*    >*/}
                {/*    </Chip>*/}
                {/*    <Chip*/}
                {/*        avatar={<Task/>}*/}
                {/*        size='small'*/}
                {/*        label={'rtest'}*/}
                {/*    >*/}
                {/*    </Chip>*/}


                {/*</Grid>*/}
            </CardActions>
        </Card>
    )
}