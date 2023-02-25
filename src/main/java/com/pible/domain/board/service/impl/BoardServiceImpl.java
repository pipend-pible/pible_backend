package com.pible.domain.board.service.impl;

import com.pible.common.entity.BoardClaimEntity;
import com.pible.common.entity.BoardEntity;
import com.pible.common.entity.TagEntity;
import com.pible.common.entity.BoradTagMappingEntity;
import com.pible.common.enums.ResponseCode;
import com.pible.common.exception.BusinessException;
import com.pible.domain.board.dao.BoardRepository;
import com.pible.domain.board.mapper.BoardMapper;
import com.pible.domain.board.model.BoardDto;
import com.pible.domain.board.model.BoardRes;
import com.pible.domain.board.service.BoardService;
import com.pible.domain.category.board.dao.BoardCategoryRepository;
import com.pible.domain.channel.dao.ChannelRepository;
import com.pible.domain.claim.board.dao.BoardClaimRepository;
import com.pible.domain.claim.board.model.BoardClaimDto;
import com.pible.domain.image.service.ImageService;
import com.pible.domain.mapping.dao.BoardTagMappingRepository;
import com.pible.domain.tag.dao.TagRepository;
import com.pible.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final ChannelRepository channelRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final BoardCategoryRepository boardCategoryRepository;
    private final BoardTagMappingRepository boardTagMappingRepository;
    private final BoardClaimRepository boardClaimRepository;
    private final ImageService imageService;
    private final BoardMapper boardMapper = BoardMapper.INSTANCE;

    @Override
    @Transactional
    public BoardRes saveBoard(MultipartHttpServletRequest request, BoardDto boardDto) {
        BoardEntity boardEntity = boardMapper.dtoToEntity(boardDto);

        boardEntity.setRelation(
                channelRepository.getReferenceById(boardDto.getChannelId()),
                // TODO 회원가입, 인증 이후
                userRepository.getReferenceById(boardDto.getUserId()),
                boardCategoryRepository.getReferenceById(boardDto.getBoardCategoryId())
        );

        boardEntity = boardRepository.save(boardEntity);

        if(CollectionUtils.isEmpty(boardDto.getTagList())) {
            return boardMapper.entityToBoardRes(boardEntity);
        }

        for(String tag : boardDto.getTagList()) {
            TagEntity tagEntity = tagRepository.findByTag(tag).orElseGet(
                    () -> tagRepository.save(TagEntity.builder().tag(tag).build())
            );

            boardTagMappingRepository.save(BoradTagMappingEntity.builder()
                    .boardEntity(boardEntity)
                    .tagEntity(tagEntity)
                    .build());
        }

        imageService.uploadImageFiles(request, boardEntity, null);

        return boardMapper.entityToBoardRes(boardEntity, boardDto.getTagList());
    }

    @Override
    public BoardRes getBoard(Long boardId) {
        return boardMapper.entityToBoardRes(
                boardRepository.findById(boardId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA))
        );
    }

    @Override
    @Transactional
    public boolean deleteBoard(Long boardId) {
        boardRepository.findById(boardId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA)).delete();
        return true;
    }

    @Override
    @Transactional
    public BoardRes modifyBoard(Long boardId, BoardDto boardDto) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));
        boardMapper.updateFromDto(boardDto, boardEntity);
        return boardMapper.entityToBoardRes(boardEntity);
    }

    @Override
    @Transactional
    public boolean increaseLikeCountOfBoard(Long boardId) {
        boardRepository.findById(boardId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA)).like();
        return true;
    }

    @Override
    public List<BoardRes> getBoardListByTag(String tag) {
        return boardTagMappingRepository.findAllByTagEntity(
                tagRepository.findByTag(tag).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA)))
                .stream()
                .map((boardTagMappingEntity -> {
                    BoardEntity boardEntity = boardTagMappingEntity.getBoardEntity();
                    Hibernate.initialize(boardEntity);
                    return boardMapper.entityToBoardRes(boardEntity);
                })).collect(Collectors.toList());
    }

    @Override
    public boolean claimBoard(Long boardId, BoardClaimDto boardClaimDto) {
        BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(() -> new BusinessException(ResponseCode.NO_DATA));

        boardClaimRepository.save(BoardClaimEntity.builder()
                .claimReason(boardClaimDto.getClaimReason())
                .boardEntity(boardEntity)
                // TODO 회원가입, 인증 이후
                .userEntity(userRepository.getReferenceById(1L))
                .build());

        return true;
    }
}
