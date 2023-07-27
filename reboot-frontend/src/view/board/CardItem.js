import {Card, CardActions, CardHeader, CardMedia, Chip} from "@mui/material";
import IconButton from "@mui/material/IconButton";
import {$error} from "../../util/snackbar-utils";
import {EditOutlined, Menu, Task} from "@mui/icons-material";
import Grid from "@mui/material/Grid";
import {useNavigate} from "react-router-dom";
import {useSelector} from "react-redux";

const getStyle = (prov, snap) => {

    return {
        ...prov.draggableProps.style,
        ...snap.isDragging && !snap.isDropAnimating && {
            // transform: ' rotate(5deg) ' + prov.draggableProps.style.transform
        }
    }
}

const CardItem = ({provided, snapshot, item}) => {

    const navigate = useNavigate();
    const bId = useSelector(sts => sts.dashboard.board?.id || null)
    return (
        <Card
            sx={{
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
            onClick={() => navigate(`/c/${item.id}`)}
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
            {
                item.coverUrl && <img
                    width='100%'
                    src={`/api/v1/resources/board/${bId}/${item.coverUrl}`}
                />
            }
            <CardHeader
                titleTypographyProps={{
                    fontSize: 'small'
                }}
                sx={{p: 1}}
                title={item.title}>
            </CardHeader>
            <CardActions
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
};
export default CardItem